package io.samancore.occupation.data.repository;

import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.PageData;

public interface OccupationRepository {

    PageData<OccupationEntity> getPageByLabel(String label, PageRequest pageRequest);
}