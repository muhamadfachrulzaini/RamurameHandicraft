package com.ramurame.handicraft.data.local;

import android.content.Context;
import com.ramurame.handicraft.data.local.dao.OrderDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class DatabaseInitializer_Factory implements Factory<DatabaseInitializer> {
  private final Provider<OrderDao> orderDaoProvider;

  private final Provider<Context> contextProvider;

  public DatabaseInitializer_Factory(Provider<OrderDao> orderDaoProvider,
      Provider<Context> contextProvider) {
    this.orderDaoProvider = orderDaoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public DatabaseInitializer get() {
    return newInstance(orderDaoProvider.get(), contextProvider.get());
  }

  public static DatabaseInitializer_Factory create(Provider<OrderDao> orderDaoProvider,
      Provider<Context> contextProvider) {
    return new DatabaseInitializer_Factory(orderDaoProvider, contextProvider);
  }

  public static DatabaseInitializer newInstance(OrderDao orderDao, Context context) {
    return new DatabaseInitializer(orderDao, context);
  }
}
