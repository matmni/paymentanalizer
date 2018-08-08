package spring.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public enum ApplicationContextSingleton {
    INSTANCE;
    static AnnotationConfigApplicationContext context;

    private ApplicationContextSingleton() {
    }

    public AnnotationConfigApplicationContext getInstance() {
        if (context == null) {
            context = new AnnotationConfigApplicationContext();

            context.register(AppConfig.class);
            context.refresh();
        }
        return context;
    }

}
