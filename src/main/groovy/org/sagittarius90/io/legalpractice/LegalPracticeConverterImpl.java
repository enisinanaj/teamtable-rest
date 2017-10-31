package org.sagittarius90.io.legalpractice;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.LegalPracticeResource;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.model.LegalPracticeModel;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

public class LegalPracticeConverterImpl extends BaseConverter implements LegalPracticeConverter {

    private static UserConverterImpl userConverter;

    @Override
    public LegalPractice createFrom(final LegalPracticeModel model) {
        return updateEntity(new LegalPractice(), model);
    }

    @Override
    public LegalPracticeModel createFrom(final LegalPractice entity) {
        LegalPracticeModel model = new LegalPracticeModel();

        String practiceId = getIdUtils().encodeId(Long.valueOf(entity.getId()));
        model.sethRef(getModelUri(practiceId));
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

    protected UriBuilder getResourcePath() {
        return getUriInfo().getBaseUriBuilder().path(LegalPracticeResource.class).path("/");
    }
}