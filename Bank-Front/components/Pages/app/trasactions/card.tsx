import { View, Text } from "react-native";
import { StyleSheet, Animated } from "react-native";
const styles = StyleSheet.create({
    row: {
        flex: 1,
        flexDirection: 'row',
        flexWrap: 'wrap',
        backgroundColor: '#0071ce',
        paddingVertical: 20,
        paddingHorizontal: 15,
    },
    columns: {
        width: '50%'
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
export default function TrasactionCard({ trasaction }) {
    return (
        <View style={styles.row}>
            <View style={styles.columns}>
                <Text style={styles.column.left.text}>{trasaction.description}</Text>
            </View>


            <View style={styles.columns}>
                <Text style={styles.column.right.text}>{trasaction.amount}€</Text>
                <Text style={styles.column.right.lable}>{trasaction.balance_after_transaction}€</Text>
            </View>
        </View>
    )
}

