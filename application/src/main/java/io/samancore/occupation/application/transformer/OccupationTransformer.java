package io.samancore.occupation.application.transformer;

import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.model.Occupation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class OccupationTransformer {

    @Inject
    Logger log;

    public Occupation toModel(OccupationEntity entity) {
        return Occupation.newBuilder().setId(entity.getId())
                .setId(entity.getId())
                .setLabel(entity.getName())
                .build();
    }

}
