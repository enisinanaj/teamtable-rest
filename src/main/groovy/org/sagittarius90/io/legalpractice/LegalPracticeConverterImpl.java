package org.sagittarius90.io.legalpractice;

import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.model.LegalPracticeModel;

public class LegalPracticeConverterImpl implements LegalPracticeConverter {

    private static UserConverterImpl userConverter;

    @Override
    public LegalPractice createFrom(final LegalPracticeModel model) {
        return updateEntity(new LegalPractice(), model);
    }

    @Override
    public LegalPracticeModel createFrom(final LegalPractice entity) {
        LegalPracticeModel model = new LegalPracticeModel();

        model.setId(entity.getId());
        model.setCreator(getUserConverter().createFrom(entity.getCreator()));
        model.setDescription(entity.getDescription());

        return model;
    }

    @Override
    public LegalPractice updateEntity(final LegalPractice entity, final LegalPracticeModel model) {
        //TODO: implement
        return entity;
    }

    private static UserConverterImpl getUserConverter() {
        if (userConverter == null) {
            userConverter = new UserConverterImpl();
        }

        return userConverter;
    }
}