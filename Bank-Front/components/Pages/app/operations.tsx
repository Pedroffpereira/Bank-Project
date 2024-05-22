import { View, Text } from "react-native";
import { StyleSheet, Animated } from "react-native";
import AntDesign from '@expo/vector-icons/AntDesign';
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
import { Link } from "expo-router";

const styles = StyleSheet.create({
    box: {
        padding: 10,
        borderWidth: 1,
        alignItems: 'center',
        height: 50,
        width: 50,
        display: 'flex',
        margin: 'auto',

    },
    operation: {
        textAlign: 'center',
        paddingHorizontal: 30,
    },
    icon: {
        margin: 'auto'
    },
    text: {
        color: '#fff',
        fontWeight: 'bold',
        fontSize: 10,
        textAlign: 'center',
        paddingTop: 5
    },
    container: {
        flexDirection: 'row',
        justifyContent: 'center',
        paddingVertical: 30,
    }
})
export default function Operations() {
    return (
        <View style={styles.container}>

            <Link href="/operations/deposit">
                <View style={styles.operation}>
                    <View style={styles.box}>
                        <AntDesign style={styles.icon} name="download" size={24} color="#fff" />
                    </View>
                    <Text style={styles.text}>Depositar</Text>
                </View>
            </Link>
            <Link href="/operations/withdrawal">
                <View style={styles.operation}>
                    <View style={styles.box}>
                        <AntDesign style={styles.icon} name="upload" size={24} color="#fff" />
                    </View>
                    <Text style={styles.text}>Levantar</Text>
                </View>
            </Link>
            <Link href="/operations/transfer">
                <View style={styles.operation}>
                    <View style={styles.box}>
                        <FontAwesome6 style={styles.icon} name="money-bill-transfer" size={24} color="#fff" />
                    </View>
                    <Text style={styles.text}>Transferir</Text>
                </View>
            </Link>
        </View>
    )
}