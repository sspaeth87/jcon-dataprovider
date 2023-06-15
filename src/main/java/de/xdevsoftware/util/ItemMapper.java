package de.xdevsoftware.util;

import jakarta.inject.Singleton;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ItemMapper {
    public <T, R> R fromItem(T sourceItem, Class<R> target) {
        final ModelMapper mapper = new ModelMapper();
        return mapper.map(sourceItem, target);
    }

    public <T, R> List<R> fromList(List<T> sourceItems, Class<R> target) {
        final List<R> items = new ArrayList<>();

        final ModelMapper mapper = new ModelMapper();
        sourceItems.forEach(item -> items.add(mapper.map(item, target)));

        return items;
    }
}
