package com.cainiao.cainiao_ui.ui.recycler;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.FileReader;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;
import java.util.WeakHashMap;

import retrofit2.http.PUT;

/**
 * Created by wuyinlei on 2017/10/22.
 */

public class MultipleItemEntity implements MultiItemEntity {

    private final ReferenceQueue<LinkedHashMap<Object, Object>> ITEM_QUEUE = new ReferenceQueue<>();
    private final LinkedHashMap<Object, Object> MULITIPLE_FIELDS = new LinkedHashMap<>();
    private final SoftReference<LinkedHashMap<Object,Object>> FILEDS_REFERENCE =
            new SoftReference<>(MULITIPLE_FIELDS, ITEM_QUEUE);

    public MultipleItemEntity(LinkedHashMap<Object,Object> fields) {
        FILEDS_REFERENCE.get().putAll(fields);
    }

    public static MultipleItemEntityBuilder builder(){
        return new MultipleItemEntityBuilder();
    }

    @Override
    public int getItemType() {
        return (int) FILEDS_REFERENCE.get().get(MultipleFields.ITEM_TYPE);
    }

    @SuppressWarnings("unchecked")
    public final <T> T getFields(String key){
        return (T) FILEDS_REFERENCE.get().get(key);
    }

    public final LinkedHashMap<?,?> getFields(){
        return FILEDS_REFERENCE.get();
    }

    public final MultipleItemEntity setField(Object key,Object value){
        FILEDS_REFERENCE.get().put(key,value);
        return this;
    }
}
