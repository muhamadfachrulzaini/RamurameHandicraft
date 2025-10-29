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
public final class GetAllOrdersUseCase_Factory implements Factory<GetAllOrdersUseCase> {
  private final Provider<OrderRepository> repositoryProvider;

  public GetAllOrdersUseCase_Factory(Provider<OrderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetAllOrdersUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetAllOrdersUseCase_Factory create(Provider<OrderRepository> repositoryProvider) {
    return new GetAllOrdersUseCase_Factory(repositoryProvider);
  }

  public static GetAllOrdersUseCase newInstance(OrderRepository repository) {
    return new GetAllOrdersUseCase(repository);
  }
}
