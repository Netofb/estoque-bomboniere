param(
    [string]$DatabaseUrl = "jdbc:postgresql://localhost:5432/bomboniere",
    [string]$DatabaseUsername = "postgres"
)

$securePassword = Read-Host "Senha do PostgreSQL" -AsSecureString
$pointer = [Runtime.InteropServices.Marshal]::SecureStringToBSTR($securePassword)
try {
    $env:DATABASE_URL = $DatabaseUrl
    $env:DATABASE_USERNAME = $DatabaseUsername
    $env:DATABASE_PASSWORD = [Runtime.InteropServices.Marshal]::PtrToStringBSTR($pointer)
    .\mvnw.cmd spring-boot:run
}
finally {
    [Runtime.InteropServices.Marshal]::ZeroFreeBSTR($pointer)
    Remove-Item Env:DATABASE_PASSWORD -ErrorAction SilentlyContinue
}
