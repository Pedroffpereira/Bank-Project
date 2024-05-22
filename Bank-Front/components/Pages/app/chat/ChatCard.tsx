import { Link } from "expo-router";
import { StyleSheet, Animated } from "react-native";

import { View, Text, Pressable } from "react-native";
const styles = StyleSheet.create({
    row: {
        flex: 1,
        flexDirection: 'row',
        flexWrap: 'wrap',
        backgroundColor: '#0071ce',
        paddingVertical: 20,
        paddingHorizontal: 15,
        width: '100%'
    },
    column: {
        left: {
            text: {
                color: '#fff',
            },
            lable: {
                color: '#fff',
                fontSize: 11,
            },
        },
        right: {
            text: {
                color: '#fff',
                textAlign: 'right',
            },
            lable: {
                color: '#fff',
                fontSize: 11,
                textAlign: 'right',
            },
        }
    },

})
export default function ChatCard({ group }) {
    return (

        <Link href={"/chatGroup/" + group.id}>
            <View style={styles.row}>
                <Text style={styles.column.left.text}>{group.description}</Text>
            </View>
        </Link>
    )
}