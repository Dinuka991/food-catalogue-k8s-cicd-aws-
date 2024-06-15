package org.dinuka.foodcatalogue.dto;

public record EmployeeDTO(Long employeeId,
                          String name,
                          String role,
                          String contactNumber,
                          String hireDate) {
    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", hireDate='" + hireDate + '\'' +
                '}';
    }
}
