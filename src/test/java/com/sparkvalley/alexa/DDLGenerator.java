package com.sparkvalley.alexa;

import com.mysema.util.ClassPathUtils;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import java.util.Set;

/**
 * Created by Barak on 4/10/2015.
 */
public class DDLGenerator {
    public static void main(String ... args) throws Exception {
        Set<Class<?>> packageClasses = ClassPathUtils.scanPackage(ClassLoader.getSystemClassLoader(), "com.sparkvalley.alexa");

        execute(packageClasses);
    }
    private static void execute(Set<Class<?>> classes) {


        AnnotationConfiguration configuration = new AnnotationConfiguration();
        Class dialect = H2Dialect.class;
        configuration.setProperty(Environment.DIALECT, dialect.getName());
        for (Class clz : classes)
            configuration.addAnnotatedClass(clz);
        SchemaExport schemaExport = new SchemaExport(configuration);
        schemaExport.setDelimiter(";");
        schemaExport.setOutputFile(String.format("%s_%s.%s ", new Object[] {"ddl", dialect.getSimpleName().toLowerCase(), "sql" }));
        boolean consolePrint = true;
        boolean exportInDatabase = false;
        schemaExport.create(consolePrint, exportInDatabase);
    }
}
