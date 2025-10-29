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
public final class GetDashboardDataUseCase_Factory implements Factory<GetDashboardDataUseCase> {
  private final Provider<OrderRepository> repositoryProvider;

  public GetDashboardDataUseCase_Factory(Provider<OrderRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetDashboardDataUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetDashboardDataUseCase_Factory create(
      Provider<OrderRepository> repositoryProvider) {
    return new GetDashboardDataUseCase_Factory(repositoryProvider);
  }

  public static GetDashboardDataUseCase newInstance(OrderRepository repository) {
    return new GetDashboardDataUseCase(repository);
  }
}
