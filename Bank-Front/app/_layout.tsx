import { Redirect, Slot, Stack } from 'expo-router';
import { SessionProvider, useSession } from './context/ctx';
export default function AppLayout() {


    // This layout can be deferred because it's not the root layout.
    return (
        <SessionProvider>
            <Slot />
        </SessionProvider>
    );
}