package com.deliveroo.orderapp.io.api;

import com.birbit.jsonapi.JsonApiResponse;
import com.deliveroo.orderapp.io.api.request.AdditionalGuidRequest;
import com.deliveroo.orderapp.io.api.request.ApiAddress;
import com.deliveroo.orderapp.io.api.request.CheckEmailRequest;
import com.deliveroo.orderapp.io.api.request.EventsRequest;
import com.deliveroo.orderapp.io.api.request.FavouriteRequest;
import com.deliveroo.orderapp.io.api.request.FederatedLoginRequest;
import com.deliveroo.orderapp.io.api.request.LoginRequest;
import com.deliveroo.orderapp.io.api.request.NotifyMeRequest;
import com.deliveroo.orderapp.io.api.request.RateOrderRequest;
import com.deliveroo.orderapp.io.api.request.RegisterDeviceRequest;
import com.deliveroo.orderapp.io.api.request.RegistrationRequest;
import com.deliveroo.orderapp.io.api.request.TokenRequest;
import com.deliveroo.orderapp.io.api.request.VerificationRequest;
import com.deliveroo.orderapp.io.api.request.basket.BasketQuoteRequest;
import com.deliveroo.orderapp.io.api.request.basket.RedeemVoucherRequest;
import com.deliveroo.orderapp.io.api.request.order.OrderCreateRequest;
import com.deliveroo.orderapp.io.api.request.subscription.SubscriptionRequest;
import com.deliveroo.orderapp.io.api.request.subscription.SubscriptionUpdateRequest;
import com.deliveroo.orderapp.io.api.response.ApiAddressListResponse;
import com.deliveroo.orderapp.io.api.response.ApiBasketQuoteResponse;
import com.deliveroo.orderapp.io.api.response.ApiCheckEmailResponse;
import com.deliveroo.orderapp.io.api.response.ApiConfig;
import com.deliveroo.orderapp.io.api.response.ApiFavouriteResponse;
import com.deliveroo.orderapp.io.api.response.ApiLocationConfig;
import com.deliveroo.orderapp.io.api.response.ApiMealCard;
import com.deliveroo.orderapp.io.api.response.ApiOrderResponse;
import com.deliveroo.orderapp.io.api.response.ApiPaymentToken;
import com.deliveroo.orderapp.io.api.response.ApiRestaurantWithMenu;
import com.deliveroo.orderapp.io.api.response.ApiRoute;
import com.deliveroo.orderapp.io.api.response.ApiUser;
import com.deliveroo.orderapp.io.api.response.ApiVoucherInfo;
import com.deliveroo.orderapp.io.api.response.ClientTokensResponse;
import com.deliveroo.orderapp.io.api.response.OrdersResponse;
import com.deliveroo.orderapp.io.api.response.PaymentTokensResponse;
import com.deliveroo.orderapp.io.api.response.UserSessionResponse;
import com.deliveroo.orderapp.io.api.response.subscription.ApiSubscription;
import com.deliveroo.orderapp.io.api.response.subscription.SubscriptionCancellationResponse;
import com.deliveroo.orderapp.io.api.v2.response.ApiDeliveryTimesResponse;
import com.deliveroo.orderapp.io.api.v2.response.ApiRestaurantListResponse;
import com.deliveroo.orderapp.model.AddressToPatch;
import com.deliveroo.orderapp.model.AddressToUpdate;
import io.reactivex.Maybe;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public abstract interface RooApiService
{
  @POST("v1/users/{userId}/addresses")
  public abstract Single<ApiAddress> addAddress(@Query("restaurant") String paramString, @Body ApiAddress paramApiAddress);
  
  @POST("v1/users/{userId}/payment-tokens")
  public abstract Single<ApiPaymentToken> addPaymentMethod(@Body TokenRequest paramTokenRequest);
  
  @POST("v1/users/{userId}/favourites/restaurants_lists_restaurants")
  public abstract Single<ApiFavouriteResponse> addToFavourites(@Body FavouriteRequest paramFavouriteRequest);
  
  @GET("v1/users/{userId}/addresses")
  public abstract Single<ApiAddressListResponse> addressesList(@Query("lat") double paramDouble1, @Query("lng") double paramDouble2, @Query("accuracy") float paramFloat);
  
  @GET("v1/users/{userId}/addresses")
  public abstract Single<ApiAddressListResponse> addressesList(@Query("restaurant") String paramString);
  
  @POST("v1/alias-additional-guid")
  public abstract Single<Object> aliasAdditionalGuid(@Body AdditionalGuidRequest paramAdditionalGuidRequest);
  
  @POST("v1/basket")
  public abstract Observable<ApiBasketQuoteResponse> basketQuote(@Body BasketQuoteRequest paramBasketQuoteRequest, @Query("track") String paramString);
  
  @POST("v1/users/{userId}/subscription/cancellation")
  public abstract Observable<SubscriptionCancellationResponse> cancelSubscription();
  
  @POST("v1/check-email")
  public abstract Single<ApiCheckEmailResponse> checkEmail(@Body CheckEmailRequest paramCheckEmailRequest);
  
  @GET("v1/payment-providers/{name}/{tld}/client-tokens")
  public abstract Single<ClientTokensResponse> clientTokenForPaymentProcessor(@Path("name") String paramString1, @Path("tld") String paramString2);
  
  @GET("v1/config")
  public abstract Single<ApiConfig> countryConfiguration(@Query("ab_tests") String paramString1, @Query("features") String paramString2, @Query("supported_countries") String paramString3);
  
  @POST("v1/users/{userId}/orders")
  public abstract Observable<ApiOrderResponse> createOrder(@Body OrderCreateRequest paramOrderCreateRequest);
  
  @DELETE("v1/users/{userId}/addresses/{addressId}")
  public abstract Maybe<Void> deleteAddress(@Path("addressId") String paramString);
  
  @DELETE("v1/users/{userId}/payment-tokens/{paymentTokenId}")
  public abstract Maybe<Void> deletePaymentMethod(@Path("paymentTokenId") String paramString);
  
  @POST("v1/events")
  public abstract Maybe<Void> events(@Body EventsRequest paramEventsRequest);
  
  @POST("v1/login/federated")
  public abstract Single<UserSessionResponse> federatedLogin(@Body FederatedLoginRequest paramFederatedLoginRequest);
  
  @GET("v1/users/{userId}/credits")
  public abstract Single<ApiCreditResponse> getCredits();
  
  @GET("v2/delivery_times")
  public abstract Observable<ApiDeliveryTimesResponse> getDeliveryTimes(@Query("lat") double paramDouble1, @Query("lng") double paramDouble2);
  
  @GET("v2/meal_cards")
  public abstract Single<JsonApiResponse<ApiMealCard[]>> getMealCardIssuers(@Query("country_code") String paramString);
  
  @GET("v1/users/{userId}/orders/{orderId}")
  public abstract Single<ApiOrderResponse> getOrder(@Path("orderId") String paramString1, @Query("track") String paramString2);
  
  @GET("v2/restaurants/{id}/delivery_times")
  public abstract Single<ApiDeliveryTimesResponse> getRestaurantDeliveryTimes(@Path("id") String paramString, @Query("lat") Double paramDouble1, @Query("lng") Double paramDouble2);
  
  @GET("v1/routes")
  public abstract Single<ApiRoute> getRoute(@Query("url") String paramString, @Query("lat") Double paramDouble1, @Query("lng") Double paramDouble2);
  
  @GET("v1/users/{userId}/subscription")
  public abstract Observable<ApiSubscription> getSubscription();
  
  @GET("v1/users/{userId}")
  public abstract Single<UserSessionResponse> getUser();
  
  @GET("v1/location-config")
  public abstract Single<ApiLocationConfig> locationConfig(@Query("lat") double paramDouble1, @Query("lng") double paramDouble2);
  
  @POST("v1/login?track=1")
  public abstract Single<UserSessionResponse> logIn(@Header("Authorization") String paramString, @Body LoginRequest paramLoginRequest);
  
  @POST("v1/logout")
  public abstract Maybe<Void> logout();
  
  @POST("v1/coverage/requests")
  public abstract Observable<Void> notifyMe(@Body NotifyMeRequest paramNotifyMeRequest);
  
  @GET("v1/users/{userId}/orders")
  public abstract Observable<OrdersResponse> orderHistory(@Query("offset") int paramInt1, @Query("limit") int paramInt2);
  
  @GET("v1/users/{userId}/orders")
  public abstract Observable<OrdersResponse> orderHistory(@Query("state") String paramString);
  
  @PATCH("v1/users/{userId}/addresses/{addressId}")
  public abstract Single<ApiAddress> patchAddress(@Path("addressId") String paramString, @Body AddressToPatch paramAddressToPatch);
  
  @GET("v1/users/{userId}/payment-tokens")
  public abstract Single<PaymentTokensResponse> paymentTokens(@Query("country") String paramString);
  
  @GET("v1/credits/redemption_status")
  public abstract Single<ApiCreditDetailsResponse> redeemCredit(@Query("status_token") String paramString);
  
  @POST("v1/users/{userId}/vouchers")
  public abstract Observable<ApiVoucherInfo> redeemVoucher(@Body RedeemVoucherRequest paramRedeemVoucherRequest);
  
  @POST("v1/users")
  public abstract Single<UserSessionResponse> register(@Header("Authorization") String paramString, @Body RegistrationRequest paramRegistrationRequest);
  
  @POST("v1/users/{userId}/devices")
  public abstract Maybe<Void> registerDevice(@Body RegisterDeviceRequest paramRegisterDeviceRequest);
  
  @DELETE("v1/users/{userId}/favourites/restaurants_lists_restaurants/{favouriteId}")
  public abstract Maybe<Void> removeFromFavourites(@Path("favouriteId") String paramString);
  
  @GET("v1/restaurants/{id}?track=1")
  public abstract Single<ApiRestaurantWithMenu> restaurant(@Path("id") String paramString1, @Query("lat") Double paramDouble1, @Query("lng") Double paramDouble2, @Query("include_unavailable") boolean paramBoolean, @Query("source_view") String paramString2, @Query("restaurant_fulfillments_supported") Boolean paramBoolean1, @Query("rsr") Long paramLong);
  
  @GET("v2/restaurants")
  public abstract Observable<ApiRestaurantListResponse> restaurants(@Query("lat") double paramDouble1, @Query("lng") double paramDouble2, @Query("favourites") boolean paramBoolean1, @Query("track") String paramString1, @Query("delivery_time") String paramString2, @Query("meal_cards_supported") boolean paramBoolean2, @Query("restaurant_fulfillments_supported") Boolean paramBoolean3, @Query("reduced_supported") Boolean paramBoolean4);
  
  @GET
  public abstract Observable<ApiRestaurantListResponse> restaurants(@Url String paramString);
  
  @POST("v1/session")
  public abstract Maybe<Void> startSession();
  
  @PATCH("v1/users/{userId}/orders/{orderId}/rating")
  public abstract Maybe<Void> submitOrderRating(@Body RateOrderRequest paramRateOrderRequest, @Path("orderId") String paramString);
  
  @POST("v1/users/{userId}/subscriptions")
  public abstract Single<ApiSubscription> subscribeUser(@Body SubscriptionRequest paramSubscriptionRequest);
  
  @HTTP(hasBody=true, method="DELETE", path="v1/users/{userId}/devices")
  public abstract Maybe<Void> unregisterDevice(@Body RegisterDeviceRequest paramRegisterDeviceRequest, @Header("Authorization") String paramString);
  
  @PATCH("v1/users/{userId}/addresses/{addressId}")
  public abstract Single<ApiAddress> updateAddress(@Path("addressId") String paramString, @Body AddressToUpdate paramAddressToUpdate);
  
  @PATCH("v1/users/{userId}?verify=phone")
  public abstract Single<UserSessionResponse> updateMobile(@Body ApiUser paramApiUser, @Query("context") String paramString);
  
  @PATCH("v1/users/{userId}/subscription")
  public abstract Observable<ApiSubscription> updateSubscription(@Body SubscriptionUpdateRequest paramSubscriptionUpdateRequest);
  
  @PATCH("v1/users/{userId}")
  public abstract Single<UserSessionResponse> updateUser(@Body ApiUser paramApiUser);
  
  @POST("v1/users/{userId}/verification")
  public abstract Maybe<Void> verifyUser(@Body VerificationRequest paramVerificationRequest);
}
