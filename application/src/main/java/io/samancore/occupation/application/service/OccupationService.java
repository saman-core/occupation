package io.samancore.occupation.application.service;

import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;

public interface OccupationService {

    PageData<Occupation> getPageByLabel(String label, PageRequest pageRequest);
}