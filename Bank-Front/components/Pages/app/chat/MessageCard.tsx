import { View, Text } from "react-native";

import { StyleSheet, Animated } from "react-native";
export default function MessageCard({ message, backgroundColor }) {
    const styles = StyleSheet.create({
        container: {
            backgroundColor: backgroundColor,
            padding: 10,
            marginVertical: 10
        },
        from: {
            fontSize: 10,
            fontWeight: "bold",
        },
        messageText: {
            fontSize: 20,
            textAlign: "left",
        }
    })

    return (
        <View style={styles.container}>

            <Text style={styles.messageText}>{message.text}</Text>
            <View >
                <Text style={styles.from}>{message.from}-{message.iban}</Text>
            </View>
        </View>
    )
}