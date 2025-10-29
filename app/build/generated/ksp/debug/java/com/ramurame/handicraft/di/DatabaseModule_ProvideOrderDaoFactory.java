package com.ramurame.handicraft.di;

import com.ramurame.handicraft.data.local.dao.OrderDao;
import com.ramurame.handicraft.data.local.database.RamurameDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideOrderDaoFactory implements Factory<OrderDao> {
  private final Provider<RamurameDatabase> databaseProvider;

  public DatabaseModule_ProvideOrderDaoFactory(Provider<RamurameDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public OrderDao get() {
    return provideOrderDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideOrderDaoFactory create(
      Provider<RamurameDatabase> databaseProvider) {
    return new DatabaseModule_ProvideOrderDaoFactory(databaseProvider);
  }

  public static OrderDao provideOrderDao(RamurameDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideOrderDao(database));
  }
}
