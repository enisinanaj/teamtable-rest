package org.sagittarius90.database.adapter.utils;

import org.hibernate.dialect.MySQLDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

public class CustomMySQLHibernateDialect extends MySQLDialect {

    public CustomMySQLHibernateDialect() {
        registerFunction("DATEDIFF", new SQLFunctionTemplate( StandardBasicTypes.INTEGER, "DATEDIFF(?1, ?2)" ) );
        registerFunction("NOW", new SQLFunctionTemplate( StandardBasicTypes.STRING, "NOW()"));
        registerFunction("DATE", new SQLFunctionTemplate( StandardBasicTypes.STRING, "DATE(?1)" ) );
    }
}
