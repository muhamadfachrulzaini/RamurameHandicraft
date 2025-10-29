package com.ramurame.handicraft.presentation.detail;

import androidx.lifecycle.SavedStateHandle;
import com.ramurame.handicraft.domain.usecase.GetOrderDetailUseCase;
import com.ramurame.handicraft.domain.usecase.UpdateOrderStatusUseCase;
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
public final class OrderDetailViewModel_Factory implements Factory<OrderDetailViewModel> {
  private final Provider<GetOrderDetailUseCase> getOrderDetailUseCaseProvider;

  private final Provider<UpdateOrderStatusUseCase> updateOrderStatusUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public OrderDetailViewModel_Factory(Provider<GetOrderDetailUseCase> getOrderDetailUseCaseProvider,
      Provider<UpdateOrderStatusUseCase> updateOrderStatusUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getOrderDetailUseCaseProvider = getOrderDetailUseCaseProvider;
    this.updateOrderStatusUseCaseProvider = updateOrderStatusUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public OrderDetailViewModel get() {
    return newInstance(getOrderDetailUseCaseProvider.get(), updateOrderStatusUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static OrderDetailViewModel_Factory create(
      Provider<GetOrderDetailUseCase> getOrderDetailUseCaseProvider,
      Provider<UpdateOrderStatusUseCase> updateOrderStatusUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new OrderDetailViewModel_Factory(getOrderDetailUseCaseProvider, updateOrderStatusUseCaseProvider, savedStateHandleProvider);
  }

  public static OrderDetailViewModel newInstance(GetOrderDetailUseCase getOrderDetailUseCase,
      UpdateOrderStatusUseCase updateOrderStatusUseCase, SavedStateHandle savedStateHandle) {
    return new OrderDetailViewModel(getOrderDetailUseCase, updateOrderStatusUseCase, savedStateHandle);
  }
}
