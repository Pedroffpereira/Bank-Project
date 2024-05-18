import { View, Text, Pressable } from "react-native";
import TrasactionCard from "./card";
import { StyleSheet, Animated } from "react-native";
const styles = StyleSheet.create({
    container: {
        display: 'flex',
        flexDirection: 'row',
        marginBottom: 5,
    },
    title: {
        color: '#fff',
        fontWeight: 'bold'
    },
    button: {
        marginLeft: 'auto',
    },
    buttonText: {
        color: 'rgb(47 161 255)',
    }

})
export default function TrasactionsList({ trasactions}) {
    console.log()
    return (
        <View >
            <View style={styles.container}>
                <Text style={styles.title}>Ultimos movimentos</Text>
                <Pressable style={styles.button}>
                    <Text style={styles.buttonText}>
                        Ver tudo
                    </Text>
                </Pressable>
            </View>
            {/* trasactions.map((item) => {
                <TrasactionCard trasaction={item} />
            }) */}
            
        </View>
    );
}