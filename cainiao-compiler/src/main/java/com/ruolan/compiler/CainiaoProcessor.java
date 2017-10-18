package com.ruolan.compiler;

import com.google.auto.common.Visibility;
import com.google.auto.service.AutoService;
import com.ruolan.annotations.AppRegisterGenerator;
import com.ruolan.annotations.EntryGenerator;
import com.ruolan.annotations.PayEntryGenerator;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * Created by wuyinlei on 2017/10/18.
 */
@AutoService(Processor.class)
@SuppressWarnings("unused")
public class CainiaoProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supprotAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> annotation : supprotAnnotations) {
            types.add(annotation.getCanonicalName());
        }
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> annotations = new LinkedHashSet<>();
        annotations.add(EntryGenerator.class);
        annotations.add(PayEntryGenerator.class);
        annotations.add(AppRegisterGenerator.class);
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generateAppRegisterCode(roundEnvironment);
        generatePayEntryCode(roundEnvironment);
        return true;
    }


    private void scan(RoundEnvironment environment,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor valueVisitor) {
        for (Element typeElement : environment.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();

            for (AnnotationMirror mirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementAnnotationValue
                        = mirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementAnnotationValue.entrySet()) {
                    entry.getValue().accept(valueVisitor, null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment environment) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(environment,EntryGenerator.class,entryVisitor);
    }


    private void generatePayEntryCode(RoundEnvironment environment){
        final PayEntryVisitor payEntryVisitor = new PayEntryVisitor();
        payEntryVisitor.setFiler(processingEnv.getFiler());
        scan(environment,PayEntryGenerator.class,payEntryVisitor);
    }

    private void generateAppRegisterCode(RoundEnvironment environment){
        final AppRegisterVisitor appRegisterVisitor = new AppRegisterVisitor();
        appRegisterVisitor.setFiler(processingEnv.getFiler());
        scan(environment,AppRegisterGenerator.class,appRegisterVisitor);
    }

}
