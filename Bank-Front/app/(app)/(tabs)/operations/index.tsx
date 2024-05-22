import { styles } from "@/components/styles/app/styles";
import { Link } from "expo-router";
import { View, Text } from "react-native";

import { StyleSheet, Animated } from "react-native";
export default function OperationssScreen() {

    const style = StyleSheet.create({
        row: {
            flexDirection: 'row',
            flexWrap: 'wrap',
            backgroundColor: '#0071ce',
            paddingVertical: 20,
            paddingHorizontal: 15,
            marginVertical: 10,
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
    return (
        <View style={styles.container}>
            
            <Text style={styles.title}>Menu Operações</Text>
            
            <Link style={style.row} href="/operations/withdrawal">
                <View>
                    <Text style={styles.text} >Levantar</Text>
                </View>
            </Link>

            <Link style={style.row} href="/operations/deposit">
                <View>
                    <Text style={styles.text}>Depositar</Text>
                </View>
            </Link>
            <Link style={style.row} href="/operations/transfer">
                <View>
                    <Text style={styles.text}>Transferir</Text>
                </View>
            </Link>
        </View>
    )
}