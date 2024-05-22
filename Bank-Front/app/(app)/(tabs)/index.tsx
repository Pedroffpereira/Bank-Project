import { View, Text, Pressable } from "react-native";
import { useSession } from "../../context/ctx";
import { useRouter } from "expo-router";
import api from "@/Configuration/api";
import { useEffect, useState } from "react";
import UserDTO from "@/DTOs/userDTO";
import { styles } from "@/components/styles/app/styles";
import TrasactionsList from "@/components/Pages/app/trasactions/list";
import Balance from "@/components/Pages/app/balance";
import Operations from "@/components/Pages/app/operations";

export default function TabOneScreen() {


    
    const { signOut, session } = useSession();

    const [user, setUser] = useState<UserDTO>(new UserDTO);
    useEffect(() => {
        async function getUser() {
            const user = await api.post("/api/v1/users/auth", null, {
                headers: {
                    Authorization: 'Bearer ' + session
                },
            }).catch(function (error) {
                signOut()
                return;
            });
            setUser(user.data)
        }
        getUser();
    }, []);

    return (
        <View style={styles.container}>
            <Balance accountNumber={user.accountNumber} balance={user.balance + "€"} />
            <Operations />
            <TrasactionsList trasactions={user.transactions} />
        </View>
    );
}