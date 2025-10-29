package com.ramurame.handicraft.di;

import android.content.Context;
import com.ramurame.handicraft.data.local.database.RamurameDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabaseModule_ProvideRamurameDatabaseFactory implements Factory<RamurameDatabase> {
  private final Provider<Context> contextProvider;

  public DatabaseModule_ProvideRamurameDatabaseFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RamurameDatabase get() {
    return provideRamurameDatabase(contextProvider.get());
  }

  public static DatabaseModule_ProvideRamurameDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new DatabaseModule_ProvideRamurameDatabaseFactory(contextProvider);
  }

  public static RamurameDatabase provideRamurameDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRamurameDatabase(context));
  }
}
