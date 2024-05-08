package io.samancore.occupation.data.repository.constants;

public class RepositoryConstants {
    public static final String AND = " AND ";
    public static final String FILTER_GENERAL_STATUS = "generalStatus = :status ";
    public static final String FILTER_STATE_ID = "state.id = :stateId";
    public static final String FILTER_MUNICIPALITY_ID = "municipality.id = :municipalityId";
    public static final String KEY_GENERAL_STATUS = "status";
    public static final String KEY_STATE_ID = "stateId";
    public static final String KEY_MUNICIPALITY_ID = "municipalityId";
    public static final String GENERAL_STATUS_KEY = "generalStatus";
    public static final String PARISH_KEY = "parish";
    public static final String MUNICIPALITY_KEY = "municipality";
    public static final String STATE_KEY = "state";
    public static final String LIKE_LABEL = "name like :name";
}
