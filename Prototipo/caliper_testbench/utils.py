import json


def read_json_data(json_filename):
    const = None
    with open(json_filename, 'r') as file:
        const = json.loads(file.read())
    return const
