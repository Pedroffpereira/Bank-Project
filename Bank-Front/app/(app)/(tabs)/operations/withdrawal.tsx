import api from "@/Configuration/api";
import { useSession } from "@/app/context/ctx";
import WithdrawDepositInput from "@/components/Pages/app/operations/WithdrawDepositInput";
import { styles } from "@/components/styles/app/styles";
import { useState } from "react";
import { View, Text, Pressable, TextInput } from "react-native";

export default function DepositPage() {

    return (
        <View style={styles.container}>
            <Text style={styles.title}>Levantar</Text>
            <View style={styles.center}>
                <WithdrawDepositInput url="/api/v1/operations/withdrawal" />
            </View>
        </View>
    )
}