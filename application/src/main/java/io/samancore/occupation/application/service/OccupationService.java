package io.samancore.occupation.application.service;

import java.util.List;

import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;

public interface OccupationService {
    List<Occupation> getAll();
    List<Occupation> searchByLabel(String label);
    PageData<Occupation> getPageByLabel(String label, PageRequest pageRequest);
    PageData<Occupation> getPage(PageRequest pageRequest);
}