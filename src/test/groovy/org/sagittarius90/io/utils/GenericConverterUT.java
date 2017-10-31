package org.sagittarius90.io.utils;

import org.junit.Before;
import org.junit.Test;
import org.sagittarius90.model.utils.AbstractModel;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GenericConverterUT {

    private static final String ANY_VALUE = "any value";
    private ModelForTest modelMocked;
    private EntityForTest entityMocked;
    GenericConverter<EntityForTest, ModelForTest> converter;

    private class EntityForTest {
        String testAttribute;

        public String getTestAttribute() {
            return testAttribute;
        }

        public void setTestAttribute(String testAttribute) {
            this.testAttribute = testAttribute;
        }
    }

    private class ModelForTest extends AbstractModel {
        String testAttribute;

        public String getTestAttribute() {
            return testAttribute;
        }

        public void setTestAttribute(String testAttribute) {
            this.testAttribute = testAttribute;
        }
    }

    @Before
    public void setUp() {
        converter = new GenericConverter<EntityForTest, ModelForTest>() {
            @Override
            public EntityForTest createFrom(ModelForTest model) {
                return updateEntity(new EntityForTest(), model);
            }

            @Override
            public ModelForTest createFrom(EntityForTest entity) {
                return modelMocked;
            }

            @Override
            public EntityForTest updateEntity(EntityForTest entity, ModelForTest model) {
                return entityMocked;
            }
        };

        modelMocked = mock(ModelForTest.class);
        entityMocked = mock(EntityForTest.class);
        
        given(modelMocked.getTestAttribute()).willReturn(ANY_VALUE);
        given(entityMocked.getTestAttribute()).willReturn(ANY_VALUE);
    }

    @Test
    public void aModelCollectionIsConvertedIntoAnEntityCollection() throws Exception {
        //given
        ArrayList<ModelForTest> modelCollection = new ArrayList<ModelForTest>();
        modelCollection.add(modelMocked);

        //when
        List<EntityForTest> result = converter.createFromModel(modelCollection);

        //then
        assert result.size() > 0;
        assert result.get(0).getTestAttribute().equals(modelCollection.get(0).getTestAttribute());
    }

}