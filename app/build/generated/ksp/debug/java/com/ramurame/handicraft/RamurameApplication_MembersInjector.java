package com.ramurame.handicraft;

import com.ramurame.handicraft.data.local.DatabaseInitializer;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RamurameApplication_MembersInjector implements MembersInjector<RamurameApplication> {
  private final Provider<DatabaseInitializer> databaseInitializerProvider;

  public RamurameApplication_MembersInjector(
      Provider<DatabaseInitializer> databaseInitializerProvider) {
    this.databaseInitializerProvider = databaseInitializerProvider;
  }

  public static MembersInjector<RamurameApplication> create(
      Provider<DatabaseInitializer> databaseInitializerProvider) {
    return new RamurameApplication_MembersInjector(databaseInitializerProvider);
  }

  @Override
  public void injectMembers(RamurameApplication instance) {
    injectDatabaseInitializer(instance, databaseInitializerProvider.get());
  }

  @InjectedFieldSignature("com.ramurame.handicraft.RamurameApplication.databaseInitializer")
  public static void injectDatabaseInitializer(RamurameApplication instance,
      DatabaseInitializer databaseInitializer) {
    instance.databaseInitializer = databaseInitializer;
  }
}
