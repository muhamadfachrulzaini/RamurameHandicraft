package com.ramurame.handicraft.presentation.orders;

import com.ramurame.handicraft.domain.usecase.GetAllOrdersUseCase;
import com.ramurame.handicraft.domain.usecase.GetOrdersByStatusUseCase;
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
public final class OrdersViewModel_Factory implements Factory<OrdersViewModel> {
  private final Provider<GetAllOrdersUseCase> getAllOrdersUseCaseProvider;

  private final Provider<GetOrdersByStatusUseCase> getOrdersByStatusUseCaseProvider;

  public OrdersViewModel_Factory(Provider<GetAllOrdersUseCase> getAllOrdersUseCaseProvider,
      Provider<GetOrdersByStatusUseCase> getOrdersByStatusUseCaseProvider) {
    this.getAllOrdersUseCaseProvider = getAllOrdersUseCaseProvider;
    this.getOrdersByStatusUseCaseProvider = getOrdersByStatusUseCaseProvider;
  }

  @Override
  public OrdersViewModel get() {
    return newInstance(getAllOrdersUseCaseProvider.get(), getOrdersByStatusUseCaseProvider.get());
  }

  public static OrdersViewModel_Factory create(
      Provider<GetAllOrdersUseCase> getAllOrdersUseCaseProvider,
      Provider<GetOrdersByStatusUseCase> getOrdersByStatusUseCaseProvider) {
    return new OrdersViewModel_Factory(getAllOrdersUseCaseProvider, getOrdersByStatusUseCaseProvider);
  }

  public static OrdersViewModel newInstance(GetAllOrdersUseCase getAllOrdersUseCase,
      GetOrdersByStatusUseCase getOrdersByStatusUseCase) {
    return new OrdersViewModel(getAllOrdersUseCase, getOrdersByStatusUseCase);
  }
}
