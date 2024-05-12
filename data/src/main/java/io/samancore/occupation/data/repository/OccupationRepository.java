package io.samancore.occupation.data.repository;

import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.PageData;

import java.util.List;

public interface OccupationRepository {

    PageData<OccupationEntity> getPageByParams(List<Long> ids, String label, PageRequest pageRequest);

    OccupationEntity getById(Long id);
}