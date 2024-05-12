package io.samancore.occupation.application.service;

import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;

import java.util.List;

public interface OccupationService {

    PageData<Occupation> getPageByParams(List<Long> ids, String label, PageRequest pageRequest);

    Occupation getById(Long id);
}