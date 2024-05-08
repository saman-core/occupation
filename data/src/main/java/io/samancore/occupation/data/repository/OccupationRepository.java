package io.samancore.occupation.data.repository;

import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.PageData;

import java.util.List;

public interface OccupationRepository {
    List<OccupationEntity> getAll();

    List<OccupationEntity> searchByLabel(String label);

    PageData<OccupationEntity> getPageByLabel(String label, PageRequest pageRequest);
    PageData<OccupationEntity> getPage(PageRequest pageRequest);
    
}