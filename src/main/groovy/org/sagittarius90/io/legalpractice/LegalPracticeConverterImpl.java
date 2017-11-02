package org.sagittarius90.io.legalpractice;

import org.sagittarius90.api.ApplicationConfig;
import org.sagittarius90.api.resources.LegalPracticeResource;
import org.sagittarius90.database.adapter.UserDbAdapter;
import org.sagittarius90.database.entity.LegalPractice;
import org.sagittarius90.database.entity.User;
import org.sagittarius90.io.user.UserConverterImpl;
import org.sagittarius90.io.utils.BaseConverter;
import org.sagittarius90.io.utils.ClassUtils;
import org.sagittarius90.io.utils.IdUtils;
import org.sagittarius90.model.LegalPracticeModel;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.Date;

public class LegalPracticeConverterImpl extends BaseConverter implements LegalPracticeConverter {

    private static UserConverterImpl userConverter;

    @Override
    public LegalPractice createFrom(final LegalPracticeModel model) {
        if (model.getCreatorId() == null) {
            throw new RuntimeException("Creator Identifier is required for new Legal Practices.");
        }

        User user = getUserDbAdapter().getUserById(extractUserId(model));

        LegalPractice legalPractice = new LegalPractice();

        legalPractice.setDescription(model.getDescription());
        legalPractice.setName(model.getName());
        legalPractice.setCreator(user);

        return legalPractice;
    }

    @Override
    public LegalPracticeModel createFrom(final LegalPractice entity) {
        LegalPracticeModel model = new LegalPracticeModel();

        String practiceId = getIdUtils().encodeId(Long.valueOf(entity.getId()));
        model.sethRef(getModelUri(practiceId));
        model.setCreator(getUserConverter().createFrom(entity.getCreator()));
        model.setDescription(entity.getDescription());
        model.setName(entity.getName());

        return model;
    }

    @Override
    public LegalPractice updateEntity(final LegalPractice entity, final LegalPracticeModel model) {

        if (model.getCreator() == null) {
            throw new RuntimeException("Creator identifier missing!");
        }

        User user = getUserDbAdapter().getUserById(extractUserId(model));

        new ClassUtils<String>().setIfNotNull(model::getDescription, entity::setDescription);
        new ClassUtils<String>().setIfNotNull(model::getName, entity::setName);
        entity.setCreator(user);

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

    public UserDbAdapter getUserDbAdapter() {
        return UserDbAdapter.getInstance();
    }

    private Integer extractUserId(LegalPracticeModel model) {
        return (int) IdUtils.getInstance().decodeId(model.getCreatorId());
    }
}