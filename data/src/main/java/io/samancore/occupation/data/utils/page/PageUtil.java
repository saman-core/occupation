package io.samancore.occupation.data.utils.page;

import io.samancore.occupation.model.PageData;
import jakarta.ws.rs.core.MultivaluedMap;

import java.util.List;
import java.util.function.Function;

import static io.samancore.occupation.data.utils.page.PageParamConstant.*;

public class PageUtil {

    private PageUtil() {
    }

    public static PageRequest getPage(MultivaluedMap<String, String> queryParameters) {
        String pageSort = queryParameters.getFirst(SORT);
        String pageOrder = queryParameters.getFirst(ORDER);
        var pageNumber = Integer.parseInt(queryParameters.getFirst(PAGE));
        var pageLimit = Integer.parseInt(queryParameters.getFirst(LIMIT));

        return PageRequest.newBuilder()
                .setPage(pageNumber)
                .setLimit(pageLimit)
                .setSort(pageSort)
                .setOrder(pageOrder)
                .build();
    }

    public static <E> PageData<E> toPageEntity(List<E> list, long total) {
        return PageData.<E>newBuilder()
                .setData(list)
                .setCount(total)
                .build();
    }

    public static <E, M> PageData<M> toPageModel(PageData<E> model, Function<? super E, M> mapper) {
        var modelList = model.getData().stream()
                .map(mapper)
                .toList();
        return PageData.<M>newBuilder()
                .setData(modelList)
                .setCount(model.getCount())
                .build();
    }
}
