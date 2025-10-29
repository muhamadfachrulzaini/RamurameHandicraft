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
public final class GetOrdersByStatusUseCase_Factory implements Factory<GetOrdersByStatusUseCase> {
  private final Provider<OrderRepository> repositoryProvider;

  public GetOrdersByStatusUseCase_Factory(Provider<OrderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetOrdersByStatusUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetOrdersByStatusUseCase_Factory create(
      Provider<OrderRepository> repositoryProvider) {
    return new GetOrdersByStatusUseCase_Factory(repositoryProvider);
  }

  public static GetOrdersByStatusUseCase newInstance(OrderRepository repository) {
    return new GetOrdersByStatusUseCase(repository);
  }
}
