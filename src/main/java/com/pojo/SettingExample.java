package com.pojo;

import java.util.ArrayList;
import java.util.List;

public class SettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SettingExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSmartIsNull() {
            addCriterion("smart is null");
            return (Criteria) this;
        }

        public Criteria andSmartIsNotNull() {
            addCriterion("smart is not null");
            return (Criteria) this;
        }

        public Criteria andSmartEqualTo(Integer value) {
            addCriterion("smart =", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartNotEqualTo(Integer value) {
            addCriterion("smart <>", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartGreaterThan(Integer value) {
            addCriterion("smart >", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartGreaterThanOrEqualTo(Integer value) {
            addCriterion("smart >=", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartLessThan(Integer value) {
            addCriterion("smart <", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartLessThanOrEqualTo(Integer value) {
            addCriterion("smart <=", value, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartIn(List<Integer> values) {
            addCriterion("smart in", values, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartNotIn(List<Integer> values) {
            addCriterion("smart not in", values, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartBetween(Integer value1, Integer value2) {
            addCriterion("smart between", value1, value2, "smart");
            return (Criteria) this;
        }

        public Criteria andSmartNotBetween(Integer value1, Integer value2) {
            addCriterion("smart not between", value1, value2, "smart");
            return (Criteria) this;
        }

        public Criteria andWaterIsNull() {
            addCriterion("water is null");
            return (Criteria) this;
        }

        public Criteria andWaterIsNotNull() {
            addCriterion("water is not null");
            return (Criteria) this;
        }

        public Criteria andWaterEqualTo(Integer value) {
            addCriterion("water =", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotEqualTo(Integer value) {
            addCriterion("water <>", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThan(Integer value) {
            addCriterion("water >", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterGreaterThanOrEqualTo(Integer value) {
            addCriterion("water >=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThan(Integer value) {
            addCriterion("water <", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterLessThanOrEqualTo(Integer value) {
            addCriterion("water <=", value, "water");
            return (Criteria) this;
        }

        public Criteria andWaterIn(List<Integer> values) {
            addCriterion("water in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotIn(List<Integer> values) {
            addCriterion("water not in", values, "water");
            return (Criteria) this;
        }

        public Criteria andWaterBetween(Integer value1, Integer value2) {
            addCriterion("water between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andWaterNotBetween(Integer value1, Integer value2) {
            addCriterion("water not between", value1, value2, "water");
            return (Criteria) this;
        }

        public Criteria andWenduIsNull() {
            addCriterion("wendu is null");
            return (Criteria) this;
        }

        public Criteria andWenduIsNotNull() {
            addCriterion("wendu is not null");
            return (Criteria) this;
        }

        public Criteria andWenduEqualTo(Integer value) {
            addCriterion("wendu =", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduNotEqualTo(Integer value) {
            addCriterion("wendu <>", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduGreaterThan(Integer value) {
            addCriterion("wendu >", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduGreaterThanOrEqualTo(Integer value) {
            addCriterion("wendu >=", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduLessThan(Integer value) {
            addCriterion("wendu <", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduLessThanOrEqualTo(Integer value) {
            addCriterion("wendu <=", value, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduIn(List<Integer> values) {
            addCriterion("wendu in", values, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduNotIn(List<Integer> values) {
            addCriterion("wendu not in", values, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduBetween(Integer value1, Integer value2) {
            addCriterion("wendu between", value1, value2, "wendu");
            return (Criteria) this;
        }

        public Criteria andWenduNotBetween(Integer value1, Integer value2) {
            addCriterion("wendu not between", value1, value2, "wendu");
            return (Criteria) this;
        }

        public Criteria andO2IsNull() {
            addCriterion("o2 is null");
            return (Criteria) this;
        }

        public Criteria andO2IsNotNull() {
            addCriterion("o2 is not null");
            return (Criteria) this;
        }

        public Criteria andO2EqualTo(Integer value) {
            addCriterion("o2 =", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2NotEqualTo(Integer value) {
            addCriterion("o2 <>", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2GreaterThan(Integer value) {
            addCriterion("o2 >", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2GreaterThanOrEqualTo(Integer value) {
            addCriterion("o2 >=", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2LessThan(Integer value) {
            addCriterion("o2 <", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2LessThanOrEqualTo(Integer value) {
            addCriterion("o2 <=", value, "o2");
            return (Criteria) this;
        }

        public Criteria andO2In(List<Integer> values) {
            addCriterion("o2 in", values, "o2");
            return (Criteria) this;
        }

        public Criteria andO2NotIn(List<Integer> values) {
            addCriterion("o2 not in", values, "o2");
            return (Criteria) this;
        }

        public Criteria andO2Between(Integer value1, Integer value2) {
            addCriterion("o2 between", value1, value2, "o2");
            return (Criteria) this;
        }

        public Criteria andO2NotBetween(Integer value1, Integer value2) {
            addCriterion("o2 not between", value1, value2, "o2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}