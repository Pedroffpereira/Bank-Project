import { View, Text } from "react-native";
import { StyleSheet, Animated } from "react-native";
const styles = StyleSheet.create({
    accountText: {

        color: '#fff',
    },
    balance: {
        color: '#fff',
    }
})
export default function Balance({accountNumber, balance}) {
    return (
        <View>
            <Text style={styles.accountText}>Contrato nº {accountNumber}</Text>
            <Text style={styles.balance}>{balance}</Text>
        </View>
    )
}