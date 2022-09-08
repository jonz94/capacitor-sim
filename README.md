# @jonz94/capacitor-sim

Capacitor plugin to get information from device's sim cards

## Install

```bash
npm install @jonz94/capacitor-sim
npx cap sync
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

**Returns:** <code>Promise&lt;<a href="#getsimcardsresult">GetSimCardsResult</a>&gt;</code>

--------------------


### checkPermissions()

```typescript
checkPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### requestPermissions()

```typescript
requestPermissions() => Promise<PermissionStatus>
```

**Returns:** <code>Promise&lt;<a href="#permissionstatus">PermissionStatus</a>&gt;</code>

--------------------


### Interfaces


#### GetSimCardsResult

| Prop           | Type                   |
| -------------- | ---------------------- |
| **`simCards`** | <code>SimCard[]</code> |


#### SimCard

| Prop                    | Type                 |
| ----------------------- | -------------------- |
| **`number`**            | <code>string</code>  |
| **`allowsVOIP`**        | <code>boolean</code> |
| **`carrierName`**       | <code>string</code>  |
| **`isoCountryCode`**    | <code>string</code>  |
| **`mobileCountryCode`** | <code>string</code>  |
| **`mobileNetworkCode`** | <code>string</code>  |


#### PermissionStatus

| Prop              | Type                                                        |
| ----------------- | ----------------------------------------------------------- |
| **`readSimCard`** | <code><a href="#permissionstate">PermissionState</a></code> |


### Type Aliases


#### PermissionState

<code>'prompt' | 'prompt-with-rationale' | 'granted' | 'denied'</code>

</docgen-api>
