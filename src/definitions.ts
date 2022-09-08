import type { PermissionState } from '@capacitor/core';

export interface PermissionStatus {
  readSimCard: PermissionState;
}

export interface SimPlugin {
  getSimCards(): Promise<GetSimCardsResult>;

  checkPermissions(): Promise<PermissionStatus>;

  requestPermissions(): Promise<PermissionStatus>;
}

export interface GetSimCardsResult {
  simCards: SimCard[];
}

export interface SimCard {
  number: string;
  allowsVOIP: boolean;
  carrierName: string;
  isoCountryCode: string;
  mobileCountryCode: string;
  mobileNetworkCode: string;
}
