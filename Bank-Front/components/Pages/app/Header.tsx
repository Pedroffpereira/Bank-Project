import { View, Text, Pressable } from "react-native";
import { NativeStackNavigationConfig } from "react-native-screens/lib/typescript/native-stack/types";
import Balance from "./balance";
import { useSession } from "@/app/context/ctx";
import { useRouter } from "expo-router";

import { StyleSheet, Animated } from "react-native";
const styles = StyleSheet.create({
    container: {
        backgroundColor: '#00559a',
        display: 'flex',
        paddingHorizontal: 10
    },
    button: {
        backgroundColor: "rgb(11, 87, 208)",
        paddingVertical: 10,
        paddingHorizontal: 20,
        marginTop: 15,
        borderRadius: 50,
        width: 'fit-content',
        marginLeft: 'auto',
    },
    text: {
        color: '#fff',
        textAlign: 'center'
    }
})
export default function Header(props) {
    console.log(props)
    const { signOut, session } = useSession();

    const router = useRouter();
    return (
        <View style={styles.container}>
            <Pressable style={styles.button} onPress={async () => {
                signOut();
                router.replace("/(auth)")
            }}>
                <Text style={styles.text}>Sair</Text>
            </Pressable>
        </View>
    )

}