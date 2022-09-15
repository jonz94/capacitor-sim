import type { PermissionState } from '@capacitor/core';

export interface PermissionStatus {
  readSimCard: PermissionState;
}

export interface SimPlugin {
  /**
   * Get information from device's sim cards.
   *
   * @since 1.0.0
   */
  getSimCards(): Promise<GetSimCardsResult>;

  /**
   * Check permission to get information from device's sim cards.
   *
   * On iOS the status is always granted.
   *
   * @since 1.0.0
   */
  checkPermissions(): Promise<PermissionStatus>;

  /**
   * Request permission to get information from device's sim cards.
   *
   * On iOS the status is always granted.
   *
   * @since 1.0.0
   */
  requestPermissions(): Promise<PermissionStatus>;
}

export interface GetSimCardsResult {
  simCards: SimCard[];
}

export interface SimCard {
  /**
   * Android only: Phone number.
   *
   * @since 1.0.0
   */
  number?: string;

  /**
   * iOS only: If this carrier allows VOIP calls to be made on its network.
   *
   * @since 1.0.0
   */
  allowsVOIP?: boolean;

  /**
   * The name of the cellular service provider.
   *
   * @since 1.0.0
   */
  carrierName: string;

  /**
   * Country code for the cellular service provider,
   * represented as an ISO 3166-1 country code string.
   *
   * @since 1.0.0
   */
  isoCountryCode: string;

  /**
   * Mobile country code (MCC) for the cellular service provider,
   * in its numeric representation.
   *
   * @since 1.0.0
   */
  mobileCountryCode: string;

  /**
   * Mobile network code (MNC) for the cellular service provider,
   * in its numeric representation.
   *
   * @since 1.0.0
   */
  mobileNetworkCode: string;
}
