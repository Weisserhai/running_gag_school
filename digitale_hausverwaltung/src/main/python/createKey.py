# start import

import subprocess
import os

# end import


# start variables 

password = "running_gag"
path = os.path.join(".", "digitale_hausverwaltung", "src", "main", "resources", "HTTPS_key.jks")

keytool_cmd = f"keytool -genkey -keyalg RSA -keystore {path} -keysize 2048 -storepass {password} -dname CN=Unknown,OU=Unknown,O=Unknown,L=Unknown,ST=Unknown,C=De"

# end varaibles

if os.path.exists(path):
    os.remove(path)
    print("Old key removed")

response = subprocess.run(keytool_cmd, shell = True)

if not response.returncode:
    print("New key generated")
else:
    print("No key generated")
