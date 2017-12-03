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
        legalPractice.setArchived(model.isArchived() ? 1 : 0);

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

        model.setArchived(entity.isArchived());

        return model;
    }

    @Override
    public LegalPractice updateEntity(final LegalPractice entity, final LegalPracticeModel model) {

        if (model.getCreatorId() != null) {
            User user = getUserDbAdapter().getUserById(extractUserId(model));
            entity.setCreator(user);
        }

        new ClassUtils<String>().setIfNotNull(model::getDescription, entity::setDescription);
        new ClassUtils<String>().setIfNotNull(model::getName, entity::setName);

        if (model.isArchived()) {
            entity.setArchived(1);
        } else {
            entity.setArchived(0);
        }

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