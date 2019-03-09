package com.cg.mobilebilling.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mobilebilling.beans.Plan;

public interface PlanDetailsDAO extends JpaRepository<Plan, Integer> {

}
