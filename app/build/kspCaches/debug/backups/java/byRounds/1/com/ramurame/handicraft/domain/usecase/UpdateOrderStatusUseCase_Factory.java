package com.ramurame.handicraft.domain.usecase;

import com.ramurame.handicraft.domain.repository.OrderRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class UpdateOrderStatusUseCase_Factory implements Factory<UpdateOrderStatusUseCase> {
  private final Provider<OrderRepository> repositoryProvider;

  public UpdateOrderStatusUseCase_Factory(Provider<OrderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateOrderStatusUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateOrderStatusUseCase_Factory create(
      Provider<OrderRepository> repositoryProvider) {
    return new UpdateOrderStatusUseCase_Factory(repositoryProvider);
  }

  public static UpdateOrderStatusUseCase newInstance(OrderRepository repository) {
    return new UpdateOrderStatusUseCase(repository);
  }
}
