<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">Sim</h3>
<p align="center"><strong><code>@jonz94/capacitor-sim</code></strong></p>
<p align="center">
  Capacitor plugin to get information from device's sim cards.
</p>

<p align="center">
  <a href="https://github.com/jonz94/capacitor-sim/actions/workflows/ci.yml"><img src="https://img.shields.io/github/actions/workflow/status/jonz94/capacitor-sim/ci.yml?branch=main&logo=github&style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@jonz94/capacitor-sim"><img src="https://img.shields.io/npm/l/@jonz94/capacitor-sim?style=flat-square" /></a>
  <a href="https://www.npmjs.com/package/@jonz94/capacitor-sim"><img src="https://img.shields.io/npm/v/@jonz94/capacitor-sim?style=flat-square" /></a>
</p>

## Install

```shell
npm install @jonz94/capacitor-sim
npx cap sync
```

## Android

### Variables

This plugin will use the following project variables (defined in your app's `variables.gradle` file):
- `$androidxCoreKTXVersion` version of `androidx.core:core-ktx` (default: `1.9.0`)
- `$kotlin_version` version of `org.jetbrains.kotlin:kotlin-stdlib-jdk7` (default: `1.8.10`)

## Configuration

No configuration required for this plugin.

## Demo

A working example can be found here: [jonz94/capacitor-sim-demo](https://github.com/jonz94/capacitor-sim-demo)

## Usage

```typescript
import { Sim } from '@jonz94/capacitor-sim';

const getSimCards = async () => {
  const { simCards } = await Sim.getSimCards();

  // console.log(simCards);

  return simCards;
}
```

## API

<docgen-index>

* [`getSimCards()`](#getsimcards)
* [`checkPermissions()`](#checkpermissions)
* [`requestPermissions()`](#requestpermissions)
* [Interfaces](#interfaces)
* [Type Aliases](#type-aliases)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### getSimCards()

```typescript
getSimCards() => Promise<GetSimCardsResult>
```

Get information from device's sim cards.

**Returns:** <code>Promise&lt;<a href="#getsimcardsresult">GetSimCardsResult</a>&gt;</code>

**Since:** 1.0.0

--------------------


### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

Check permission to get information from device's sim cards.

On iOS the status is always granted.

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

**Since:** 1.0.0

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

Request permission to get information from device's sim cards.

On iOS the status is always granted.

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

**Since:** 1.0.0

--------------------


### Interfaces


#### GetSimCardsResult

| Prop           | Type                   |
| -------------- | ---------------------- |
| **`simCards`** | <code>SimCard[]</code> |


#### SimCard

| Prop                    | Type                 | Description                                                                                       | Since |
| ----------------------- | -------------------- | ------------------------------------------------------------------------------------------------- | ----- |
| **`number`**            | <code>string</code>  | Android only: Phone number.                                                                       | 1.0.0 |
| **`allowsVOIP`**        | <code>boolean</code> | iOS only: If this carrier allows VOIP calls to be made on its network.                            | 1.0.0 |
| **`carrierName`**       | <code>string</code>  | The name of the cellular service provider.                                                        | 1.0.0 |
| **`isoCountryCode`**    | <code>string</code>  | Country code for the cellular service provider, represented as an ISO 3166-1 country code string. | 1.0.0 |
| **`mobileCountryCode`** | <code>string</code>  | Mobile country code (MCC) for the cellular service provider, in its numeric representation.       | 1.0.0 |
| **`mobileNetworkCode`** | <code>string</code>  | Mobile network code (MNC) for the cellular service provider, in its numeric representation.       | 1.0.0 |


#### PermissionStatus

| Prop              | Type                                                        |
| ----------------- | ----------------------------------------------------------- |
| **`readSimCard`** | <code><a href="#permissionstate">PermissionState</a></code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>

</docgen-api>

## Changelog

See [CHANGELOG.md](https://github.com/jonz94/capacitor-sim/blob/main/CHANGELOG.md).

## License

See [LICENSE](https://github.com/jonz94/capacitor-sim/blob/main/LICENSE).
