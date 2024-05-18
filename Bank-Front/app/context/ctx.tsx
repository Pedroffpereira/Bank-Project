import React from 'react';
import { useStorageState } from './useStorageState';
import api from '@/Configuration/api';
import { useRouter } from 'expo-router';
const AuthContext = React.createContext<{
    signIn: (loginRequest) => void;
    signOut: () => void;
    signUp: (signUpRequest) => void;
    session?: string | null;
    isLoading: boolean;
}>({
    signIn: (loginRequest) => null,
    signOut: () => null,
    signUp: (signUpRequest) => null,
    session: null,
    isLoading: false,
});

// This hook can be used to access the user info.
export function useSession() {
    const value = React.useContext(AuthContext);
    if (process.env.NODE_ENV !== 'production') {
        if (!value) {
            throw new Error('useSession must be wrapped in a <SessionProvider />');
        }
    }

    return value;
}



export function SessionProvider(props: React.PropsWithChildren) {
    const [[isLoading, session], setSession] = useStorageState('session');

    return (
        <AuthContext.Provider
            value={{
                signIn: async (loginRequest) => {
                    // Perform sign-in logic here
                    const { data } = await api.post("/api/v1/users/login", loginRequest);
                    setSession(data.token);
                },
                signOut: () => {
                    setSession(null);
                },
                signUp: async (signUpRequest) => {
                    await api.post("/api/v1/users/registation", signUpRequest);
                },
                session,
                isLoading,
            }}>
            {props.children}
        </AuthContext.Provider>
    );
}
