import pandas as pd
import numpy as np
from utils import read_json_data
import os
if __name__ == '__main__':
    json_tests = os.listdir("testbenches")

    for json_test in json_tests:
        file_name = json_test.replace(".json", "")
        json_data = read_json_data("testbenches/"+json_test)
        digits = json_data['digits']
        # Para las pruebas asumimos medicion positiva y en mm, y asumimos que vienen en BCD
        common_json_data = read_json_data("common_digits.json")

        digits = {**digits, **common_json_data['digits']}

        listed_digits = [[x, y] for x, y in digits.items()]

        sorted_digits = sorted(listed_digits, key=lambda x: int(x[0][1:]))
        print(sorted_digits)
        digits_in_binary = [bin(digit)[2:].zfill(4) for _, digit in sorted_digits]
        print(digits_in_binary)
        # todo: aca los digitos  en realidad hay que flipearlos (ej: el 5 en decimal point)
        # edit: en realidad no, por como se envian por el analog, se flipean solos, con lo cual llega lo que
        # mandaria el calibre
        bitstream = np.array([0]+[int(el) for el in "".join(digits_in_binary)])
        print(bitstream)
        pd.DataFrame(bitstream).to_csv("csv/"+file_name+".csv", header=False, index=False)

        clk = 2e3
        # en cada clock manda un bit
        bit_duration = 1/clk

        print("Envio todos los digitos en:")
        print(round(4*13*bit_duration*1e3), "ms")


