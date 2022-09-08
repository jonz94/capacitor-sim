import { registerPlugin } from '@capacitor/core';

import type { SimPlugin } from './definitions';

const Sim = registerPlugin<SimPlugin>('Sim', {});

export * from './definitions';
export { Sim };
