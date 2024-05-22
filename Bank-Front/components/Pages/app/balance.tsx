import { View, Text } from "react-native";
import { StyleSheet, Animated } from "react-native";
const styles = StyleSheet.create({
    container: {
        justifyContent: 'center',
        backgroundColor: '#095b9d',
        padding: 10,
        borderRadius: 50,
        alignContent: 'center'
    },
    accountText: {
        color: '#fff',
        textAlign: 'center',
    },
    balance: {
        color: '#fff',
        fontSize: 18,
        textAlign: 'center',
        fontWeight: 'bold'
    }
})
export default function Balance({ accountNumber, balance }) {
    return (
        <View style={styles.container}>
            <Text style={styles.accountText}>Contrato nº {accountNumber}</Text>
            <Text style={styles.balance}>{balance}</Text>
        </View>
    )
}